import { NavigationContainer } from '@react-navigation/native';

import DrawerRoutes from './drawer.routes';
import AuthStack from './AuthStack';



export default function Routes() {
    
    const auth = false;

    return (
        <NavigationContainer>
            {auth ? <DrawerRoutes/> : <AuthStack/>}
        </NavigationContainer>
    )
}