import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Feather, Entypo, Ionicons } from '@expo/vector-icons';

import Painel from '../screens/usuario/Painel';
import Historico from '../screens/transacao/Historico';
import Cartoes from '../screens/cartao/Cartoes';
import CadastroResgistro from '../screens/transacao/CadastroRegistro';
import RegistroCartao from '../screens/cartao/RegistroCartao';
import CadastroCartao from '../screens/cartao/CadastroCartao';

import Contas from '../screens/conta/Contas';
import CadastroConta from '../screens/conta/CadastroConta';

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
                name="PainelTab"
                component={PainelNavigation}
                options={{
                    tabBarIcon: ({ color, size }) => <Feather name='home' color={color} size={size}/>,
                    tabBarLabel: 'Painel'
                }}
            />
            <Tab.Screen 
                name="HistoricoTab"
                component={HistoricoNavigation}
                options={{
                    tabBarIcon: ({ color, size }) => <Ionicons name='newspaper-outline' color={color} size={size}/>,
                    tabBarLabel: 'Histórico'
                }}
            />

            <Tab.Screen 
                name="ContasTab"
                component={ContasNavigation}
                options={{
                    tabBarIcon: ({ color, size }) => <Ionicons name='wallet' color={color} size={size}/>,
                    tabBarLabel: 'Contas'
                }}
            />

            <Tab.Screen
                name="CartoesTab"
                component={CartaoNavigation}
                options={{
                    tabBarIcon: ({ color, size }) => <Entypo name='credit-card' color={color} size={size}/>,
                    tabBarLabel: 'Cartões'
                }}
            />
            
        </Tab.Navigator>
    )
}

// Rotas Stack da tela Painel
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

// Rotas Stack da tela Painel
const HistoricoStack = createNativeStackNavigator();

function HistoricoNavigation() {
    return(
        <HistoricoStack.Navigator
            initialRouteName="Historico"
            screenOptions={{ 
                headerShown: false,
            }}
        >
            <HistoricoStack.Screen
                name="Historico"
                component={Historico}
            />
            <HistoricoStack.Screen
                name="CadastroRegistro"
                component={CadastroResgistro}
            />
        </HistoricoStack.Navigator>
    )
}

// Rotas Stack da tela Cartões
const CartaoStack = createNativeStackNavigator();

function CartaoNavigation() {
    return(
        <CartaoStack.Navigator
            initialRouteName="Cartões"
            screenOptions={{ 
                headerShown: false,
            }}
        >
            <CartaoStack.Screen
                name="Cartões"
                component={Cartoes}
            />
            <CartaoStack.Screen
                name="CadastroCartao"
                component={CadastroCartao}
            />
            <CartaoStack.Screen
                name="RegistroCartao"
                component={RegistroCartao}
            />
        </CartaoStack.Navigator>
    )
}

/*Rotas stack da tela de contas bancarias*/

const ContaStack = createNativeStackNavigator();

function ContasNavigation() {
    return(
        <ContaStack.Navigator
            initialRouteName='Contas'
            screenOptions={{
                headerShown: false,
            }}
        >
            <ContaStack.Screen
                name="Contas"
                component={Contas}
            />
            <ContaStack.Screen
                name="CadastroConta"
                component={CadastroConta}
            />


        </ContaStack.Navigator>
    )
}