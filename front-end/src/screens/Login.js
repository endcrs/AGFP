import { useState } from 'react';
import { Image, StyleSheet, Text, View, TouchableOpacity, Alert } from 'react-native';
import { useNavigation } from '@react-navigation/native';

import logo from '../asset/logo.png';
import { Button } from '../components/Button.js';
import { InputText } from '../components/InputText.js';

import { useAuth } from '../contexts/Auth';

import { formatCPF } from '../utils/formatCPF';


export default function Login() {
  const navigation = useNavigation();
  //Hook para utilizar as funções de autenticação
  const {singIn} = useAuth();
  
  const [cpf, setCPF] = useState('');
  const [senha, setSenha] = useState('');

  //adicionando pontuação ao CPF
  const adicionarPontuacao = (text) => {
    const formattedCPF = formatCPF(text);
    setCPF(formattedCPF);
  }

  //tirando pontuação ao CPF para realizar o acesso a aplicação
  const login = () =>{
    const cpfsemPontuacao = cpf.replace(/\D/g, '');
    //realizando acesso a API
    singIn(cpfsemPontuacao, senha);
  }
  
  return (
    <View style={styles.container}>
      <Image
        resizeMode="contain"
        source={logo}
        style={{width: 180, height: 180, marginBottom: 30,}}
      />
      <InputText
        onChangeText={adicionarPontuacao}
        value={cpf}
        placeholder="CPF"
        maxLength={14}        
        keyboardType="numeric"
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
        onPress={login}
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