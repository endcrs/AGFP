import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Perfil from '../screens/usuario/Perfil';
import CadastroResgistro from '../screens/transacao/CadastroRegistro';

const Stack = createNativeStackNavigator();

export default function StackRoutes() {
    return(
        <Stack.Navigator screenOptions={{ headerShown: false }}>
            <Stack.Screen 
                name="Perfil"
                component={Perfil}
            />
            <Stack.Screen 
                name="CadastroRegistro"
                component={CadastroResgistro}
            />
        </Stack.Navigator>
    )
}