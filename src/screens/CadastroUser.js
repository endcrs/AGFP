import { Alert, Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";


import logo from '../asset/logo.png';
import { InputText } from "../components/InputText";
import { useState } from "react";
import { Button } from "../components/Button";
import { useNavigation } from "@react-navigation/native";
import api from "../services/api";

export default function CadastroUser() {
  const navigation = useNavigation();

  /*Variaveis de acesso */
  const [nome, setNome] = useState('');
  const [cpf, setCPF] = useState('');
  const [dataNasc, setDataNasc] = useState('');
  const [numCelular, setNumCelular] = useState('');
  const [senha, setSenha] = useState('');
  const [confSenha, setConfSenha] = useState('');

  
  async function cadastroUsuario(){

    if(nome != "" && cpf != "" && dataNasc != "" &&  
      numCelular != "" && senha != "" && confSenha != ""
    ){
        if(senha == confSenha){
            await api.post("/usuarios",
                {
                    nomeCompleto: nome,
                    cpf: cpf,
                    dataNascimento: dataNasc,
                    celular: numCelular,
                    senha: senha,
                    senhaConfirmada: confSenha,
                }
            ).then(function (response) {
                Alert.alert('Cadastro realizado com sucesso!');
                navigation.goBack();
            }).catch(function (error){
                Alert.alert('Cadastro não realizado!');
                console.log(error)
            });
        }else{
            Alert.alert("Senha e a confirmação estão diferentes!")
        }

    }else{
      Alert.alert("Nenhuma caixa pode está vazia!");
    }



  }

  return (
    <View style={styles.container}>
      <Image 
        resizeMode="contain"
        source={logo}
        style={{width: 180, height: 180, marginBottom: 30,}}
      />

      <InputText
        onChangeText={setNome}
        value={nome}
        placeholder="Nome"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setCPF}
        value={cpf}
        maxLength={11}    
        placeholder="CPF"
        keyboardType="numeric"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setDataNasc}
        value={dataNasc}
        placeholder="Data de Nascimento"
        keyboardType="numeric"
        placeholderTextColor="#727272"
      />
      <InputText
        onChangeText={setNumCelular}
        value={numCelular}
        placeholder="xx xxxx-xxxx"
        keyboardType="numeric"
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
        value={confSenha}
        placeholder="Confirmar Senha"
        placeholderTextColor="#727272"
      />

      <Button
        style={{marginTop: 20, width:150}}
        title='Cadastrar'
        onPress={() => cadastroUsuario()}
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