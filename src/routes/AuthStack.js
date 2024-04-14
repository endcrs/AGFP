import { createNativeStackNavigator } from '@react-navigation/native-stack';

import SignInScreen from '../screens/SignInScreen'

const Stack = createNativeStackNavigator();

// tela rota de acesso a tela de login
export default function AuthStack() {
    return(
        <Stack.Navigator screenOptions={{ headerShown: false }}>
            <Stack.Screen 
                name="SignInScreen"
                component={SignInScreen}
            />
        </Stack.Navigator>
    )
}