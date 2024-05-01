import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";


import logo from '../asset/logo.png';
import { InputText } from "../components/InputText";
import { useState } from "react";
import { Button } from "../components/Button";
import { useNavigation } from "@react-navigation/native";

export default function CadastroUser() {
  const navigation = useNavigation();

  /*Variaveis de acesso */
  const [Nome, setNome] = useState('');
  const [cpf, setCPF] = useState('');
  const [dataNasc, setDataNasc] = useState('');
  const [senha, setSenha] = useState('');
  const [ConfSenha, setConfSenha] = useState('');


  return (
    <View style={styles.container}>
      <Image 
        resizeMode="contain"
        source={logo}
        style={{width: 180, height: 180, marginBottom: 30,}}
      />

      <InputText
        onChangeText={setNome}
        value={Nome}
        placeholder="Nome"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setCPF}
        value={cpf}
        placeholder="CPF"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setDataNasc}
        value={dataNasc}
        placeholder="Data de Nascimento"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setSenha}
        value={senha}
        placeholder="Senha"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setConfSenha}
        value={ConfSenha}
        placeholder="Confirmar Senha"
        placeholderTextColor="#727272"
      />

      <Button
        style={{marginTop: 20, width:150}}
        title='Cadastrar'
      />
      
      <TouchableOpacity 
        style={{marginTop:30}}
        onPress={() => navigation.goBack()}
      >
        <Text style={styles.spam}>Já Possui conta? Efetue o login</Text>
      </TouchableOpacity>
    </View>
  )
}


const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#000',
      alignItems: 'center',
      justifyContent: 'center',
    },
    spam: {
      fontSize: 15,
      color: "#9F9898",
      fontWeight: '500',
      textDecorationLine: 'underline',
    }
  });