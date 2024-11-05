import { AuthData } from '../contexts/Auth';
import api from './api';

// função que irá realizar o login na aplicação
async function singIn(cpf: string, password: string): Promise<AuthData>{

    return new Promise((resolve, reject) => {
        
        api.post("/users/login",
            {
                cpf:cpf,
                senha:password
            }
        ).then(function (response) {
            resolve({
                token: response.data.id,
                cpf: response.data.cpf,
                name: response.data.nome
            })
        }).catch(function (error) {
            reject(new Error('Credenciais Inválidas'));
        })
    });
   
}

export const authService = {singIn};