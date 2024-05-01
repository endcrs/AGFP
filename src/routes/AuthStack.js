import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Login from '../screens/Login'
import CadastroUser from '../screens/CadastroUser';

const Stack = createNativeStackNavigator();

// tela rota de acesso a tela de login
export default function AuthStack() {
    return(
        <Stack.Navigator screenOptions={{ headerShown: false }}>
           <Stack.Screen 
                name="Login"
                component={Login}
            />
            <Stack.Screen 
                name="CadastroUser"
                component={CadastroUser}
            />
        </Stack.Navigator>
    )
}