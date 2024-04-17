import 'react-native-gesture-handler';

import Routes from './src/routes';
import { AuthProvider } from './src/contexts/Auth';

export default function App() {
  return (
    <AuthProvider>
      <Routes/>
    </AuthProvider>
  );
}
