import { StyleSheet, Text, View } from 'react-native';
import { Button } from '../components/Button';
import { useAuth } from '../contexts/Auth';
import { InputText } from '../components/InputText';
import { useEffect, useState } from 'react';
import { formatCPF } from '../utils/formatCPF';
import { formatDate } from '../utils/formatDataNasc';
import { formatPhoneNumber } from '../utils/formatPhone';

export default function Perfil() {
  //Hook para signout
  const {signOut, authData} = useAuth();

  const [nome, setNome] = useState('');
  const [cpf, setCPF] = useState('');
  const [dataNasc, setDataNasc] = useState('15/05/2001');
  const [numCelular, setNumCelular] = useState('(11) 98545-4523'); 
  const [saldo, setSaldo] = useState('1.995,00'); 
  
  const [isEditable, setIsEditable] = useState(false); 
  const [btnEditar, setBtnEditar] = useState('Editar');
  const [color, setColor] = useState('#727272');
  

  //puxando os dados do usuário
  useEffect(() => {
    //adiciona pontuação ao Cpf
    setNome(authData.name);
    adicionarPontuacaoCpf(authData.cpf);
  }, []);

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


  //atualização do usuário
  const editarUsuario = () => {
    // se o botão foi igual a editar, irá realizar a atualização das caixas para editar as informações
    if(btnEditar == 'Editar'){
      setIsEditable(true)
      setBtnEditar('Atualizar')
      setColor('#fff');
    }
    
    //quando o botão foi igual a atualizar, será ser realizado a atualização no banco e depois alterado novamente para bloquear as paginas
    if(btnEditar == 'Atualizar'){
      
      // PUT para a API ficará aqui

      setIsEditable(false)
      setBtnEditar('Editar')
      setColor('#727272');
    }
    
  }

  //cancelando a atualização
  const cancelarAtualizarcao = () =>{
    setIsEditable(false)
    setBtnEditar('Editar')
    setColor('#727272');
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
        color={color}
        editable={isEditable}
      />
      <InputText
        onChangeText={adicionarPontuacaoPhone}
        value={numCelular}
        placeholder="Celular"
        color={color}
        editable={isEditable}
      />
      <InputText
        onChangeText={setSaldo}
        value={saldo}
        placeholder="Saldo"
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