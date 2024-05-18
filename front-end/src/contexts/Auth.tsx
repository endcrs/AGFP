import React, { useContext, useEffect } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { createContext, useState } from "react";
import { authService } from "../services/authServices";
import { Alert } from "react-native";


//Objeto a ser criado no login 
export interface AuthData{
    token: string;
    cpf: string;
    name: string;
};

// interface de contexto para entrar na aplicação e sair
interface AuthContextData {
    authData?: AuthData;
    singIn: (cpf: string, password: string) => Promise<AuthData>;
    signOut: () => Promise<void>;
    loading: boolean;
};

//interface para aceitar o children do arquivo APP para iniciar as rotas e ter a informção do contexto do autenticação
interface Routes{
    children: React.ReactNode;
}

// contexto de autenticação
export const AuthContext = createContext<AuthContextData>(
    {} as  AuthContextData,
);



//Volvendo toda a aplicação no contexto de autenticação
export const AuthProvider: React.FC<Routes> = ({children}) => {
    const [authData, setAuth] = useState<AuthData>();
    const [loading, setLoading] = useState(true);

    //logando com asyncStorage
    useEffect(()=>{
        loadFromStorage();
    }, []);

    //pegando os dados da storage e setando novamente a varial com os dados do usuário já logado
    async function loadFromStorage(){
        const auth =  await AsyncStorage.getItem('@AuthData');
        if(auth){
            setAuth(JSON.parse(auth) as AuthData);
        }
        setLoading(false);
    }

   async function singIn(cpf: string, password: string){
        try{
            const auth = await authService.singIn(cpf, password);
            // para components ter acesso aos valores
            setAuth(auth);
            
            //salvando dados do usuário no asyncstorage
            AsyncStorage.setItem('@AuthData', JSON.stringify(auth));

            return auth;
        }catch (error){
            Alert.alert(error.message, 'Tente Novamente');
        }
    }

    //deslogando o usuário da aplicação
    async function signOut(): Promise<void>{
        setAuth(undefined);   
        AsyncStorage.removeItem('@AuthData');
    }
    
    return (
        <AuthContext.Provider value={{authData, loading, singIn, signOut}}>
            {children}
        </AuthContext.Provider>
    )
}

export function useAuth(){
    const context = useContext(AuthContext);
    return context;
}