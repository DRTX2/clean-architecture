import { useEffect, useState } from "react";
import UserCard from "../components/UserCard";
import { useUserStore } from "../store/userStore";
import { createUser, updateUser, deleteUser } from "../services/userService";
import { User } from "../models/user";
import "./UserManager.css"; // suponiendo que creás este CSS

const initialForm: User & { password?: string } = {
  id: 0,
  name: "",
  email: "",
  password: "",
};

const UserManager = () => {
  const { users, loading, fetchUsers } = useUserStore();
  const [form, setForm] = useState<typeof initialForm>(initialForm);
  const [editing, setEditing] = useState<boolean>(false);

  useEffect(() => {
    fetchUsers();
  }, [fetchUsers]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      if (editing) {
        const safeForm = {
          ...form,
          password: form.password || "12345678",
        };
        await updateUser(safeForm);
      } else {
        await createUser(form);
      }

      setForm(initialForm);
      setEditing(false);
      fetchUsers();
    } catch (error) {
      console.error("Error al guardar usuario", error);
    }
  };

  const handleEdit = (user: User) => {
    setForm({ ...user, password: "" });
    setEditing(true);
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteUser(id);
      fetchUsers();
    } catch (error) {
      console.error("Error al eliminar usuario", error);
    }
  };

  return (
  <div className="p-8 max-w-screen-xl mx-auto">
    <h2 className="text-2xl font-bold mb-6 text-center">Gestión de Usuarios</h2>

    <form
      onSubmit={handleSubmit}
      className="bg-white p-6 rounded-md shadow-md mb-8 grid md:grid-cols-4 gap-4"
    >
      <input
        type="text"
        name="name"
        placeholder="Nombre"
        value={form.name}
        onChange={handleChange}
        className="col-span-2 md:col-span-1 px-4 py-2 border rounded"
        required
      />
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={form.email}
        onChange={handleChange}
        className="col-span-2 md:col-span-1 px-4 py-2 border rounded"
        required
      />
      {!editing && (
        <input
          type="password"
          name="password"
          placeholder="Contraseña"
          value={form.password}
          onChange={handleChange}
          className="col-span-2 md:col-span-1 px-4 py-2 border rounded"
          required
        />
      )}

      <div className="flex items-center gap-2 col-span-2 md:col-span-1">
        <button type="submit" className="bg-teal-600 text-white px-4 py-2 rounded w-full">
          {editing ? "Actualizar" : "Agregar"}
        </button>
        {editing && (
          <button
            type="button"
            onClick={() => {
              setForm(initialForm);
              setEditing(false);
            }}
            className="bg-gray-400 text-white px-4 py-2 rounded w-full"
          >
            Cancelar
          </button>
        )}
      </div>
    </form>

    {!loading && (
      <div className="grid sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {users.map((user) => (
          <div key={user.id} className="bg-white p-4 rounded shadow">
            <UserCard user={user} />
            <div className="flex justify-between mt-4">
              <button
                onClick={() => handleEdit(user)}
                className="bg-slate-700 text-white px-3 py-1 rounded"
              >
                Editar
              </button>
              <button
                onClick={() => handleDelete(user.id)}
                className="bg-red-500 text-white px-3 py-1 rounded"
              >
                Eliminar
              </button>
            </div>
          </div>
        ))}
      </div>
    )}

    {!loading && users.length === 0 && (
      <p className="text-center text-gray-500 mt-8">No hay usuarios disponibles.</p>
    )}
  </div>
);
};

export default UserManager;
