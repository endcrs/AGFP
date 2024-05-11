import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Feather, Entypo, Ionicons } from '@expo/vector-icons';

import Painel from '../screens/Painel';
import Historico from '../screens/Historico';
import Cartoes from '../screens/Cartoes';
import CadastroResgistro from '../screens/CadastroRegistro';

const Tab = createBottomTabNavigator()

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
                component={PainelNavigation}
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

const PainelStack = createNativeStackNavigator();

function PainelNavigation() {
    return(
        <PainelStack.Navigator
            initialRouteName="Painel"
            screenOptions={{ 
                headerShown: false,
            }}
        >
            <PainelStack.Screen
                name="Painel"
                component={Painel}
            />
            <PainelStack.Screen
                name="CadastroRegistro"
                component={CadastroResgistro}
            />
        </PainelStack.Navigator>
    )
}