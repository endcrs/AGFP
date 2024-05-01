import { Image, StyleSheet, Text, View, TouchableOpacity, Alert } from 'react-native';

import logo from '../asset/logo.png';

import { useState } from 'react';
import { Button } from '../components/Button.js';
import { InputText } from '../components/InputText.js';
import { useAuth } from '../contexts/Auth';
import { useNavigation } from '@react-navigation/native';

export default function Login() {
  const navigation = useNavigation();
  //Hook para utilizar as funções de autenticação
  const {singIn} = useAuth();
  
  const [cpf, setCPF] = useState('');
  const [senha, setSenha] = useState('');
  
  return (
    <View style={styles.container}>
      <Image
        resizeMode="contain"
        source={logo}
        style={{width: 180, height: 180, marginBottom: 30,}}
      />
      <InputText
        onChangeText={setCPF}
        value={cpf}
        placeholder="CPF"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setSenha}
        value={senha}
        secureTextEntry
        placeholder="Senha"
        placeholderTextColor="#727272"
      />

      <Button title='Entrar no App'
        style={{marginTop: 20}}
        onPress={() => singIn(cpf, senha)}
      />

      <TouchableOpacity 
        style={{marginTop:30}}
        onPress={() => navigation.navigate('CadastroUser')}
      >
        <Text style={styles.spam}>Não tem conta? Cadastre-se</Text>
      </TouchableOpacity>
        
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
  },
  spam: {
    fontSize: 15,
    color: "#9F9898",
    fontWeight: '500',
    textDecorationLine: 'underline',
  }
});