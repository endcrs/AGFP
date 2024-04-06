import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Feather, Entypo, Ionicons } from '@expo/vector-icons';

import Inicio from '../screens/Inicio';
import Historico from '../screens/Historico';
import Cartoes from '../screens/Cartoes';

const Tab = createBottomTabNavigator();

export default function TabRoutes(){
    return (
        <Tab.Navigator screenOptions={{ headerShown: false }}>
            <Tab.Screen 
                name="painel"
                component={Inicio}
                options={{
                    tabBarIcon: ({ color, size }) => <Feather name='home' color={color} size={size}/>,
                    tabBarLabel: 'Painel'
                }}
            />
            <Tab.Screen 
                name="historico"
                component={Historico}
                options={{
                    tabBarIcon: ({ color, size }) => <Ionicons name='newspaper-outline' color={color} size={size}/>,
                    tabBarLabel: 'Histórico'
                }}
            />
            <Tab.Screen 
                name="cartoes"
                component={Cartoes}
                options={{
                    tabBarIcon: ({ color, size }) => <Entypo name='credit-card' color={color} size={size}/>,
                    tabBarLabel: 'Cartões'
                }}
            />
        </Tab.Navigator>
    )
}