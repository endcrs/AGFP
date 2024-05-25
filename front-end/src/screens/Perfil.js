import { Alert, StyleSheet, Text, View } from 'react-native';
import { Button } from '../components/Button';
import { useAuth } from '../contexts/Auth';
import { InputText } from '../components/InputText';
import { useEffect, useState } from 'react';
import { formatCPF } from '../utils/formatCPF';
import { convertDateToAPIFormat, convertDateToFormFormat, formatDate } from '../utils/formatDataNasc';
import { formatPhoneNumber } from '../utils/formatPhone';
import api from '../services/api';

export default function Perfil() {
  //Hook para signout
  const {signOut, authData} = useAuth();

  const [nome, setNome] = useState('');
  const [cpf, setCPF] = useState('');
  const [dataNasc, setDataNasc] = useState('');
  const [numCelular, setNumCelular] = useState(''); 
  const [saldo, setSaldo] = useState(''); 
  
  
  const [previousText, setPreviousText] = useState('');
  const [isEditable, setIsEditable] = useState(false); 
  const [btnEditar, setBtnEditar] = useState('Editar');
  const [color, setColor] = useState('#727272');
  

  //puxando os dados do usuário
  useEffect(() => {
    //puxando os dados do usuário assim que a pagina é acionada
    puxarUsuario();
  }, []);

  	//puxando os dados do usuário
	async function puxarUsuario() {
		await api.get(`/usuarios/${authData.token}`)
		.then(function (response){
			setNome(response.data.nomeCompleto),
			adicionarPontuacaoCpf(response.data.cpf),
			formataDataNascForm(response.data.dataNascimento),
			adicionarPontuacaoPhone(response.data.celular),
			setSaldo(response.data.saldo)
		}).catch(function (error){
			//caso o banco retorne um erro, irá aparesentar a mensagem para ajustar no cadastro
			//Alert.alert('Erro ao Puxar usuário!', 'usuário não encontrado');
		});
  	}


  //adiciona pontuação ao Cpf
  const adicionarPontuacaoCpf = (text) => {
    const formattedCPF = formatCPF(text);
    setCPF(formattedCPF);
  }

  //adiciona pontuações na data de nascimento
  const adicionarPontuacaoDateNasc = (text) => {
      const formattedDate = formatDate(text);
      setDataNasc(formattedDate);
  };
  
  //formada data vinda da API para ser adicionada ao form
  const formataDataNascForm = (text) => {
	const formatToform = convertDateToFormFormat(text);
	adicionarPontuacaoDateNasc(formatToform);
  }

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


  //atualização do usuário
  const  editarUsuario = async () => {
    // se o botão foi igual a editar, irá realizar a atualização das caixas para editar as informações
    if(btnEditar == 'Editar'){
      setIsEditable(true)
      setBtnEditar('Atualizar')
      setColor('#fff');
    }
    
    //quando o botão foi igual a atualizar, será ser realizado a atualização no banco e depois alterado novamente para bloquear as paginas
    if(btnEditar == 'Atualizar'){
		//tirando as pontuações dos campos
		const numCelularSemPontuacao = numCelular.replace(/\D/g, '');
		const dataNascParaAPI = convertDateToAPIFormat(dataNasc);
		
      // Atualizando o usuário
	  await api.put('usuarios',
		{
			id: authData.token,
			nomeCompleto: nome,
			dataNascimento: dataNascParaAPI,
			celular: numCelularSemPontuacao,
			saldo: 0,
		}

	  ).then(function (response) {
		//Informa que o cadastro foi um sucesso e direciona para a pagina de login
			Alert.alert('Atualização realizada com sucesso!');
			puxarUsuario();
			setBtnEditar('Editar');
			setColor('#727272');
			setIsEditable(false);
		}).catch(function (error){
			//caso o banco retorne um erro, irá aparesentar a mensagem para ajustar no cadastro
			Alert.alert(
				'Atualização não realizada!', 'Não foi possivel atualizar o usuário, tente novamente');
		});
		
    }
    
  }

  //cancelando a atualização
  const cancelarAtualizarcao = () =>{
    puxarUsuario();
    setBtnEditar('Editar');
    setColor('#727272');
	setIsEditable(false);
  }


  return (
    <View style={styles.container}>
      <Text style={styles.title}>Meu Perfil</Text>
      <InputText
        onChangeText={setNome}
        value={nome}
        placeholder="Nome"
        color={color}
        editable={isEditable}
      />

      <InputText
        onChangeText={adicionarPontuacaoCpf}
        value={cpf}
        placeholder="CPF"
        color={'#727272'}
        editable={false}
      />
      
      <InputText
        onChangeText={adicionarPontuacaoDateNasc}
        value={dataNasc}
        placeholder="Data Nascimento"
        keyboardType="numeric"
        color={color}
        editable={isEditable}
      />
      <InputText
        onChangeText={adicionarPontuacaoPhone}
        value={numCelular}
        placeholder="Celular"
        keyboardType="numeric"
        color={color}
        editable={isEditable}
      />
      <View style={styles.containerBtn}>
        <Button style={{width:120}} title={btnEditar} onPress={() => editarUsuario() } />

        {btnEditar == 'Atualizar' ? (
          <Button style={{backgroundColor:'red', width:120}}  title='Cancelar' onPress={() => cancelarAtualizarcao() } />
          ) : ( null )
        }
      </View>
      
      <Button style={{backgroundColor:'red', marginTop: 100}} title='Sair do App' onPress={() => signOut() } />
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
    color: 'white',
    fontWeight: 'bold',
    marginBottom: 30,
  },
  containerBtn:{
    width: '100%',
    justifyContent: 'space-evenly',
    flexDirection: 'row',
  }
});