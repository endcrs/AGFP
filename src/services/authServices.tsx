import { AuthData } from '../contexts/Auth';

// funtção que irá realizar o login na aplicação
async function singIn(cpf: string, password: string): Promise<AuthData>{
    return new Promise((resolve, reject) => {
        
        //simulação de consulta a API
        setTimeout(() => {
            if(password === '123456'){
                resolve({
                    token: 'fake-token',
                    cpf,
                    name: 'David Silva'
                })
            }else {
                reject(new Error('Credenciais Inválidas'));
            }
        }, 500)
    });
}

export const authService = {singIn};