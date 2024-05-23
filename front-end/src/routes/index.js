import { NavigationContainer } from '@react-navigation/native';

import DrawerRoutes from './drawer.routes';
import AuthStack from './AuthStack';
import { useAuth } from '../contexts/Auth';
import { Text, View } from 'react-native';
import { StatusBar } from 'expo-status-bar';


export default function Routes() {

    //setando o hook de contexto e validando se o usuário está logado ou não
    const {authData, loading} = useAuth();

    //tela de loading para esperar a API retornar os dados
    if(loading){
        return(
            <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                <Text>Carregando.........</Text>
            </View>
        )
    }

    return (
        <NavigationContainer>
            <StatusBar barStyle='light-content' />
           {authData ? <DrawerRoutes/> : <AuthStack/>}
        </NavigationContainer>
    )
}