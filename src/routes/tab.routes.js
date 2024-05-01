import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Feather, Entypo, Ionicons } from '@expo/vector-icons';

import Painel from '../screens/Painel';
import Historico from '../screens/Historico';
import Cartoes from '../screens/Cartoes';

const Tab = createBottomTabNavigator();

export default function TabRoutes(){
    return (
        <Tab.Navigator 
            
            screenOptions={{ 
                headerShown: false,
                
                tabBarStyle:{
                    backgroundColor: '#131313', // Cor do fundo da barra de guias
                },
                tabBarActiveTintColor:'#00C530', // Cor do icone ativo
                tabBarInactiveTintColor: '#fff', // Cor do icone inativo
            }}
        >
            <Tab.Screen 
                name="Painel"
                component={Painel}
                options={{
                    tabBarIcon: ({ color, size }) => <Feather name='home' color={color} size={size}/>,
                    tabBarLabel: 'Painel'
                }}
            />
            <Tab.Screen 
                name="Historico"
                component={Historico}
                options={{
                    tabBarIcon: ({ color, size }) => <Ionicons name='newspaper-outline' color={color} size={size}/>,
                    tabBarLabel: 'Histórico'
                }}
            />
            <Tab.Screen 
                name="Cartoes"
                component={Cartoes}
                options={{
                    tabBarIcon: ({ color, size }) => <Entypo name='credit-card' color={color} size={size}/>,
                    tabBarLabel: 'Cartões'
                }}
            />
        </Tab.Navigator>
    )
}