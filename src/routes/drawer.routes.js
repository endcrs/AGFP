import { createDrawerNavigator } from '@react-navigation/drawer';
import { Feather, Entypo } from '@expo/vector-icons';

import TabRoutes from './tab.routes';
import StackRoutes from './stack.routes';

const Drawer = createDrawerNavigator();

export default function DrawerRoutes() {
    return(
        <Drawer.Navigator screenOptions={{ title: '',
            headerTintColor:'#fff', // mudando a cor do icone 'MenuBurger'
            drawerActiveBackgroundColor: '#474747', // Cor do fundo da opção de tela ativa
            drawerActiveTintColor: '#00C530', // cor do icone e texto da opção ativa
            drawerInactiveBackgroundColor: '#474747',  // Cor do fundo da opção de tela Inativa
            drawerInactiveTintColor: '#fff', // cor do icone e texto da opção Inativa
            
            headerStyle:{
                backgroundColor:'#000', // Mudando a cor da header
            },
            drawerLabelStyle:{
                fontSize: 17, // Almentando a fonte do texto
            },
            drawerStyle:{
                backgroundColor: '#131313', // Cor do fundo da barra de guias  
            },        
        }}>
            <Drawer.Screen 
                name="Inicio"
                component={TabRoutes}
                options={{
                    drawerIcon: ({ color, size }) => <Feather name='home' color={color} size={size}/>,
                    drawerLabel: 'Painel'
                }}
            />

            <Drawer.Screen 
                name="MinhaConta"
                component={StackRoutes}
                options={{
                    drawerIcon: ({ color, size }) => <Feather name='user' color={color} size={size}/>,
                    drawerLabel: 'Minha Conta'
                }}
            />
        </Drawer.Navigator>
    )
}