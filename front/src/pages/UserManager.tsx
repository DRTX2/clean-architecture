import { useEffect, useState } from "react";
import UserCard from "../components/UserCard";
import { useUserStore } from "../store/userStore";
import { createUser, updateUser, deleteUser } from "../services/userService";
import { User } from "../models/user";

const initialForm: User = { id: 0, name: "", email: "" };

const UserManager = () => {
  const { users, loading, fetchUsers } = useUserStore();

  const [form, setForm] = useState<User>(initialForm);
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
        await updateUser(form);
      } else {
        await createUser(form);
      }

      setForm(initialForm);
      setEditing(false);
      fetchUsers(); // refresca la lista
    } catch (error) {
      console.error("Error al guardar usuario", error);
    }
  };

  const handleEdit = (user: User) => {
    setForm(user);
    setEditing(true);
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteUser(id);
      fetchUsers(); // refresca la lista
    } catch (error) {
      console.error("Error al eliminar usuario", error);
    }
  };

  return (
    <div>
      <h2>Users</h2>

      {loading && <p>Loading...</p>}

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={form.name}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={form.email}
          onChange={handleChange}
          required
        />
        <button type="submit">{editing ? "Update" : "Add"}</button>
        {editing && (
          <button type="button" onClick={() => { setForm(initialForm); setEditing(false); }}>
            Cancel
          </button>
        )}
      </form>

      {!loading && Array.isArray(users) && (
        <ul>
          {users.map((user) => (
            <li key={user.id}>
              <UserCard user={user} />
              <button onClick={() => handleEdit(user)}>Edit</button>
              <button onClick={() => handleDelete(user.id)}>Delete</button>
            </li>
          ))}
        </ul>
      )}

      {!loading && users.length === 0 && <p>No users available.</p>}
    </div>
  );
};

export default UserManager;
