import { create } from 'zustand';
import { User } from '../models/user';
import { getUsers } from '../services/userService';

interface UserState {
  users: User[];
  loading: boolean;
  fetchUsers: () => void;
}

export const useUserStore = create<UserState>((set) => ({
  users: [],
  loading: false,
  fetchUsers: async () => {
    set({ loading: true });
    try {
      console.log("Fetching users...");
      const response = await getUsers();
      set({ users: response.data, loading: false });
    } catch (error) {
      console.error('Error loading users', error);
      set({ users: [], loading: false });
    }
  }
}));