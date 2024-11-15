import { useEffect, useState } from "react";
import { Alert, StyleSheet, Text, View } from "react-native";
import { useNavigation } from "@react-navigation/native";

import { InputSelect, InputText, MaskedInput } from "../../components/InputText";
import { Button } from "../../components/Button";


import { useAuth } from "../../contexts/Auth";
import api from "../../services/api";

import { convertDateToAPIFormat, formatDate } from "../../utils/formatData";
import { formatValueToAPI } from "../../utils/formatValue";


export default function CadastroResgistro() {
	const {authData} = useAuth();
	const navigation = useNavigation();
	
	const [titulo, setTitulo] = useState('');
	const [valor, setValor] = useState('');
  const [banco, setBanco] = useState('');
	const [categoria, setCategoria] = useState('');
	const [tipoTransacao, setTipoTransacao] = useState('');

	// Listas
	const [dataTipoTransacao, setDataTipoTransacao] = useState([]);
	const [dataCategoria, setDataCategoria] = useState([]);
	const [dataBanco, setDataBanco] = useState([]);


	//puxando os categorias e cartões
	useEffect(() => {
		// Listar cartões do usuários
		api.get(`/accounts/by-user/${authData.id}`)
			.then((response)=> setDataBanco(response.data))
			.catch((err)=>console.log(err));

		// Listar cartões da categoria
		api.get('/categories')
			.then((response)=> setDataCategoria(response.data))
			.catch((err)=>console.log(err));

		// Listar tipo de transação
		api.get('/transaction-types')
			.then((response)=> setDataTipoTransacao(response.data))
			.catch((err)=>console.log(err));
	}, []);

	async function cadastroTransacao()
	{
		if (titulo != "" && valor != "" && categoria != "" && banco != "")
		{
			const valorSemFormatacao =  formatValueToAPI(valor);
			// //realizando cadastro na API
			console.log(valorSemFormatacao);
			await api.post("/accounts/transactions",
				{
					titulo: titulo,
					idConta: banco,
					valor: valorSemFormatacao,
					status: "ATIVO",
					categoria: categoria,
					tipo: tipoTransacao
			
				}
			).then(function (response) {
				//Informa que o cadastro foi um sucesso e direciona para a pagina de login
				Alert.alert('Cadastro realizado com sucesso!', 'Sua Transação foi inserido com sucesso!');
				navigation.goBack();
			}).catch(function (error){

				if(error.response.status == 400){
					//caso apresente o erro 400 irá informar a mensagem disponivel no headers do erro
					Alert.alert(
						'Cadastro não realizado!',
						'Certifique-se de que o saldo seja suficiente.');
					
				}else{
					
					Alert.alert(
						'Cadastro não realizado!',
						'Tente novamente mais tarde!');
				}
				

					console.log(error.response.headers);
			});
		}else{
			Alert.alert('Preencha todos os campos!', 'Tenha atenção ao preencher, ele não poderá ser alterado!');
		}
	}

  return(
		<View style={styles.container}>
			<Text style={styles.title}>NOVO REGISTRO</Text>

			<InputText
				onChangeText={setTitulo}
				value={titulo}
				placeholder="Digite um titulo para da transação"
				maxLength={15}
				placeholderTextColor="#727272"
			/>

			<MaskedInput
				type={'money'}
				value={valor}
				onChangeText={text => setValor(text)}
				style={styles.input}
        keyboardType="numeric"
				placeholder="Digite o valor da transação"
				placeholderTextColor="#727272"
				options={{
					precision: 2,
					separator: ',',
					delimiter: '.',
					unit: 'R$ ',
					suffixUnit: ''
				}}
			/>

			<InputSelect
				value={tipoTransacao}
				data={dataTipoTransacao}
				placeholder="Tipo de Transação"
				placeholderTextColor="#727272"
				labelField="descricao"
				valueField="codigo"
				onChange={item => {
					setTipoTransacao(item.codigo)
				}}
			/>

			<InputSelect
				value={banco}
				data={dataBanco}
				placeholder="Selecione o banco"
				placeholderTextColor="#727272"
				labelField="banco.descricao"
				valueField="id"
				onChange={item => {
					setBanco(item.id)
				}}
			/>	

			<InputSelect
				value={categoria}
				data={dataCategoria}
				placeholder="Selecione a Categoria"
				placeholderTextColor="#727272"
				labelField="descricao"
				valueField="codigo"
				onChange={item => {
					setCategoria(item.codigo)
				}}
			/>

			<Button
				style={{marginTop:20, width:150}}
				title='Salvar'
				onPress={() => cadastroTransacao()}
			/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
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