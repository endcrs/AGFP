import { Alert, StyleSheet, Text, View } from "react-native";
import { InputSelect, InputText, MaskedInput } from "../../components/InputText";
import { useNavigation } from "@react-navigation/native";
import { useAuth } from "../../contexts/Auth";
import { useEffect, useState } from "react";
import api from "../../services/api";
import { Button } from "../../components/Button";
import { formatValueToAPI } from "../../utils/formatValue";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { IconBack } from "../../components/IconBack";

export default function CadastroConta(){
  const navigation = useNavigation();
  const {authData} = useAuth();

  // Variaveis
  const [saldo, setSaldo] = useState('');
  const [banco, setBanco] = useState('');
  const [titleButton, setTitleButton] = useState('Salvar');

  // variáveis de dados dos inputs de select
  const [dataBanco, setDataBanco] = useState([]);

  useEffect(() => {
    //buscando bancos pré-cadastrados
    api.get('/banks')
    .then((response)=> setDataBanco(response.data))
    .catch((err)=>console.log(err));
  }, []);


  //Cadastro conta
  async function CadastrarConta() {
   


    if(saldo != "" && banco != ""){
    
      const saldoToApi = formatValueToAPI(saldo);

      await api.post("/accounts", 
        {
          idUsuario: authData.id,
          saldo: saldoToApi,
          banco: banco
        }
      ).then(function (response){
        // Caso de sucesso ao cadastro
        Alert.alert('Cadastro Realizado com sucesso!', 'Sua conta bancaria foi criada com sucesso!');
        navigation.goBack();
      }).catch(function (error){
        //caso não haja sucesso no cadastro
        Alert.alert('Cadastro Não realizado',
        error.response.headers.mensagem);
      });

    }else{
      Alert.alert('Preencha todos os campos!', 'Tenha atenção ao preencher, ele não poderá ser alterado!');
    }
  }

  return(
    <View style={styles.container}>
    
    <IconBack/>
    
    <View style={styles.formWrapper}>
        <Text style={styles.title}>NOVA CONTA BANCARIA</Text>

        
        <InputSelect
          data={dataBanco}
          placeholder="Banco"
          placeholderTextColor="#727272"
          value={banco}
          labelField="descricao"
          valueField="codigo"
          onChange={item => {
            setBanco(item.codigo)
          }}
        />

        <MaskedInput
          type={'money'}
          value={saldo}
          keyboardType="numeric"
          onChangeText={saldo => setSaldo(saldo)}
          style={styles.input}
          placeholder="Digite o saldo atual da conta"
          placeholderTextColor="#727272"
          options={{
            precision: 2,
            separator: ',',
            delimiter: '.',
            unit: 'R$ ',
            suffixUnit: ''
          }}
        />

        
        <Button
        style={{marginTop:20, width:150}}
        title={titleButton}
        onPress={ () => CadastrarConta()}
        />
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    
  },
	formWrapper:{
		flex: 1,
    backgroundColor: '#000',
    justifyContent: 'center',
    alignItems: 'center',
	},
  title: {
    marginBottom: 50,
    fontSize:22,
    color: 'white',
    fontWeight: 'bold',
  }
});