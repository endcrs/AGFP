import { createNativeStackNavigator } from '@react-navigation/native-stack';

import TabRoutes from './tab.routes';
import Perfil from '../screens/Perfil';

const Stack = createNativeStackNavigator();

export default function StackRoutes() {
    return(
        <Stack.Navigator screenOptions={{ headerShown: false }}>
            <Stack.Screen 
                name="inicio"
                component={Perfil}
            />
        </Stack.Navigator>
    )
}