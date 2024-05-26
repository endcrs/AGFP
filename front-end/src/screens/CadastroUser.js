import { Alert, Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";

import logo from '../asset/logo.png';
import { InputText } from "../components/InputText";
import { useState } from "react";
import { Button } from "../components/Button";
import { useNavigation } from "@react-navigation/native";
import api from "../services/api";
import { formatCPF } from "../utils/formatCPF";
import { convertDateToAPIFormat, formatDate } from "../utils/formatDataNasc";
import { formatPhoneNumber } from "../utils/formatPhone";

export default function CadastroUser() {
  const navigation = useNavigation();

  /*Variaveis de acesso */
  const [nome, setNome] = useState('');
  const [cpf, setCPF] = useState('');
  const [dataNasc, setDataNasc] = useState('');
  const [numCelular, setNumCelular] = useState('');
  const [senha, setSenha] = useState('');
  const [confSenha, setConfSenha] = useState('');
  
  
  const [previousText, setPreviousText] = useState('');


    //adiciona pontuação ao Cpf
    const adicionarPontuacaoCpf = (text) => {
        const formattedCPF = formatCPF(text);
        setCPF(formattedCPF);
    }

    //adicona pontuações na data de nascimento
    const adicionarPontuacaoDateNasc = (text) => {
        const formattedDate = formatDate(text);
        setDataNasc(formattedDate);
    };

    //adiciona pontuações numero do cliente
    const adicionarPontuacaoPhone = (text) => {
        const formattedPhone = formatPhoneNumber(text);

        if (text.length < previousText.length) {
            // mantendo o texto sem formatação formatação
            setNumCelular(text);
        } else {
            // setando o numero formatato na caixa de texto
            setNumCelular(formattedPhone);
        }
        // mantendo o valor do campo, ao tentar excluir o número
        setPreviousText(text);
    }
  

    // realizando cadastro do usuário 
  async function cadastroUsuario(){

    //validando se as caixas estão vazias
    if(nome != "" && cpf != "" && dataNasc != "" &&  
      numCelular != "" && senha != "" && confSenha != ""
    ){  
        //verificando as senha se estão iguais
        if(senha == confSenha){
            
            //tirando as pontuções para enviar os dados para o banco
            const cpfsemPontuacao = cpf.replace(/\D/g, '');
            const numCelularSemPontuacao = numCelular.replace(/\D/g, '');
            const dataNascParaAPI = convertDateToAPIFormat(dataNasc);
            
            //realizando cadastro na API
            await api.post("/usuarios",
                {
                    nomeCompleto: nome,
                    cpf: cpfsemPontuacao,
                    dataNascimento: dataNascParaAPI,
                    celular: numCelularSemPontuacao,
                    saldo: 0,
                    senha: senha,
                    senhaConfirmada: confSenha,
                }
            ).then(function (response) {
                //Informa que o cadastro foi um sucesso e direciona para a pagina de login
                Alert.alert('Cadastro realizado com sucesso!');
                navigation.goBack();
            }).catch(function (error){
                //caso o banco retorne um erro, irá aparesentar a mensagem para ajustar no cadastro
                Alert.alert(
                    'Cadastro não realizado!',
                    error.response.headers.mensagem);
					
					console.log(error.response);


            });
        }else{
            Alert.alert('Cadastro não realizado!',"Senha e a confirmação de senha precisam ser iguais!")
        }

    }else{
      Alert.alert('Cadastro não realizado!',"Nenhuma caixa pode está vazia!");
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
        onChangeText={adicionarPontuacaoCpf}
        value={cpf}
        maxLength={14}    
        placeholder="CPF"
        keyboardType="numeric"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={adicionarPontuacaoDateNasc}
        value={dataNasc}
        placeholder="DD/MM/AAAA"
        keyboardType="numeric"
        placeholderTextColor="#727272"
      />
      <InputText
        onChangeText={adicionarPontuacaoPhone}
        value={numCelular}
        placeholder="xx xxxx-xxxx"
        maxLength={15}
        keyboardType="numeric"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setSenha}
        value={senha}
        placeholder="Senha"
        secureTextEntry
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setConfSenha}
        value={confSenha}
        secureTextEntry
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