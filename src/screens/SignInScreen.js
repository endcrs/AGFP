import { Image, StyleSheet, View } from 'react-native';

import logo from '../asset/logo.png';

import { useState } from 'react';
import { Button } from '../components/Button.js';
import { CaixaDeTexto } from '../components/TextInput.js';

export default function Inicio() {
  const [cpf, setCPF] = useState('');
  const [senha, setSenha] = useState('');
  
  return (
    <View style={styles.container}>
      <Image
         resizeMode="contain"
         source={logo}
         style={{width: 180, height: 180, marginBottom: 30,}}
      />
      <CaixaDeTexto
        onChangeText={setCPF}
        value={cpf}
        placeholder="CPF"
        placeholderTextColor="#727272"
      />
      <CaixaDeTexto
        onChangeText={setSenha}
        value={senha}
        secureTextEntry
        placeholder="Senha"
        placeholderTextColor="#727272"
      />

      <Button title='Entrar no App'
        style={{marginTop: 20}}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize:22,
    fontWeight: 'bold',
  }
});