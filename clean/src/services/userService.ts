import axios from "axios";
import { User } from "../models/user";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

const API=`${BASE_URL}/users`;

console.log("API:", API);

export const getUsers = () =>axios.get<User[]>(API);
export const getUser = (id:number) =>axios.get<User>(`${API}/${id}`);
export const createUser = (user: User) => axios.post<User>(API, user);
export const updateUser = (user: User) => axios.put<User>(`${API}/${user.id}`, user);
export const deleteUser = (id: number) => axios.delete(`${API}/${id}`);