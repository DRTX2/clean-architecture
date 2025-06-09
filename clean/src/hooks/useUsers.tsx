import { useEffect, useState } from "react";
import { getUsers } from "../services/userService";
import { User } from "../models/user";

export function useUsers() {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        getUsers().then(res => {
            setUsers(res.data);
            setLoading(false);
        }).catch(err => {
            console.error("Error fetching users", err);
            setUsers([]);
            setLoading(false);
        });
    },[]);

    return { users, loading }; 
}