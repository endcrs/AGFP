import { useEffect, useState } from "react";
import { Alert, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { useNavigation } from "@react-navigation/native";

import { InputSelect, InputText, MaskedInput } from "../../components/InputText";
import { Button } from "../../components/Button";
import { IconBack } from "../../components/IconBack";

import { useAuth } from "../../contexts/Auth";
import api from "../../services/api";

import { formatValueToAPI } from "../../utils/formatValue";



export default function CadastroResgistro() {
	const {authData} = useAuth();
	const navigation = useNavigation();
	
	const [titulo, setTitulo] = useState('');
	const [parcelas, setParcelas] = useState('');
	const [valor, setValor] = useState('');
  	const [banco, setBanco] = useState('');
  	const [cartao, setCartao] = useState('');
	const [categoria, setCategoria] = useState('');
	const [tipoTransacao, setTipoTransacao] = useState('');
	const [tipoCompra, setTipoCompra] = useState('');

	const [isRecipe, setIsRecipe] = useState('RECEITA');
	const [isAccount, setIsAccount] = useState('CONTA');

	// Listas
	const [dataTipoTransacao, setDataTipoTransacao] = useState([]);
	const [dataCategoria, setDataCategoria] = useState([]);
	const [dataCartao, setDataCartao] = useState([]);
	const [dataBanco, setDataBanco] = useState([]);
	const dataTipoCompra = [
		{
			id: 0,
			codigo: 'CONTA CORRENTE',
			descricao: 'Conta Corrente'
		},
		{
			id: 1,
			codigo: 'CARTAO DE CREDITO',
			descricao: 'Cartão de Crédito'
		}
	]


	//puxando os categorias e cartões
	useEffect(() => {
		// Listar contas do usuários
		api.get(`/accounts/by-user/${authData.id}`)
			.then((response)=> setDataBanco(response.data))
			.catch((err)=>console.log(err));

		// Listar cartões do usuários
		api.get(`/cards/by-user/${authData.id}`)
			.then((response)=> setDataCartao(response.data))
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

		if(isRecipe == 'DESPESA' && categoria == ""){
			return Alert.alert('Cadastro não realizado!', 'Selecione a categoria da transação do tipo despesa!');
		}else{
			if (isAccount == 'CARTAO DE CREDITO') {
				if (titulo != "" && valor != "" && cartao != "" && parcelas != ""){
					await postApiRegistroCartao(categoria);
				}else{
					Alert.alert('Preencha todos os campos!', 'Tenha atenção ao preencher, ele não poderá ser alterado!');
				}
			} else if (isAccount == 'CONTA CORRENTE'){
				if (titulo != "" && valor != "" && banco != ""){
					await postApiRegistro(categoria);
				}else{
					Alert.alert('Preencha todos os campos!', 'Tenha atenção ao preencher, ele não poderá ser alterado!');
				}
			}
		}

		
	}

	const postApiRegistro = async (categoria) => {
		
		//formatando valor para enviar a API
		const valorSemFormatacao =  formatValueToAPI(valor);
		// caso o cadastro seja de uma receita ele irá enviar apenas uma categoria generica, pois no banco o campo é alterado para nula automaticamente
		const categoriaAPI = categoria != '' ? categoria : 'ALIMENTACAO';
		
		// //realizando cadastro na API
		await api.post("/accounts/transactions",
			{
				titulo: titulo,
				idConta: banco,
				valor: valorSemFormatacao,
				status: "ATIVO",
				categoria: categoriaAPI,
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
			

				console.log(error.response);
		});
	}

	const postApiRegistroCartao = async (categoria) => {
		
		//formatando valor para enviar a API
		const valorSemFormatacao =  formatValueToAPI(valor);
		// caso o cadastro seja de uma receita ele irá enviar apenas uma categoria generica, pois no banco o campo é alterado para nula automaticamente
		const categoriaAPI = categoria != '' ? categoria : 'ALIMENTACAO';
		
		// //realizando cadastro na API
		await api.post("/cards/transactions",
			{
				idCartao: cartao, // Pegar id do cartão listado/selecionado
				titulo: titulo,
				valor: valorSemFormatacao,
				status: "ATIVO",
				categoria: categoriaAPI,
				tipo: tipoTransacao,
				numeroParcelas: 1,
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

				console.log(error.response);
		});
	}

  return(
		<View style={styles.container}>

			<IconBack/>

			<View style={styles.formWrapper}>
				<Text style={styles.title}>NOVO REGISTRO</Text>

				<InputSelect
					value={tipoCompra}
					data={dataTipoCompra}
					placeholder="Tipo de compra"
					placeholderTextColor="#727272"
					labelField="descricao"
					valueField="codigo"
					onChange={item => {
						setTipoCompra(item.codigo);
						setIsAccount(item.codigo);
					}}
				/>

				<InputText
					onChangeText={setTitulo}
					value={titulo}
					placeholder="Digite um titulo para da transação"
					maxLength={20}
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
						setTipoTransacao(item.codigo);
						setIsRecipe(item.codigo);
					}}
				/>

				{isAccount == 'CARTAO DE CREDITO' ? (
					<InputSelect
						value={cartao}
						data={dataCartao}
						placeholder="Selecione o cartão de crédito"
						placeholderTextColor="#727272"
						labelField="nome"
						valueField="id"
						onChange={item => {
							setCartao(item.id)
						}}
					/>
					) : (
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
					)
				}

				{isRecipe == 'RECEITA' ? ('') :
					(
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
					)
				}

				<Button
					style={{marginTop:20, width:150}}
					title='Salvar'
					onPress={() => cadastroTransacao()}
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