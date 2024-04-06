import { createDrawerNavigator } from '@react-navigation/drawer';
import { Feather, Entypo } from '@expo/vector-icons';

import TabRoutes from './tab.routes';
import StackRoutes from './stack.routes';

const Drawer = createDrawerNavigator();

export default function DrawerRoutes() {
    return(
        <Drawer.Navigator screenOptions={{ title: '' }}>
            <Drawer.Screen 
                name="inicio"
                component={TabRoutes}
                options={{
                    drawerIcon: ({ color, size }) => <Feather name='home' color={color} size={size}/>,
                    drawerLabel: 'Inicio'
                }}
            />

            <Drawer.Screen 
                name="perfil"
                component={StackRoutes}
                options={{
                    drawerIcon: ({ color, size }) => <Feather name='user' color={color} size={size}/>,
                    drawerLabel: 'Minha Conta'
                }}
            />
        </Drawer.Navigator>
    )
}