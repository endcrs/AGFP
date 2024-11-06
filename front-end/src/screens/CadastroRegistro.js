import { useEffect, useState } from "react";
import { Alert, StyleSheet, Text, View } from "react-native";
import { useNavigation } from "@react-navigation/native";

import { InputSelect, InputText, MaskedInput } from "../components/InputText";
import { Button } from "../components/Button";


import { useAuth } from "../contexts/Auth";
import api from "../services/api";

import { convertDateToAPIFormat, formatDate } from "../utils/formatData";


export default function CadastroResgistro() {
	const {authData} = useAuth();
	const navigation = useNavigation();
	
	const [titulo, setTitulo] = useState('');
	const [valor, setValor] = useState('');
	const [cartao, setCartao] = useState('');
	const [data, setData] = useState('');
	const [categoria, setCategoria] = useState('');
	const [tipoTransacao, setTipoTransacao] = useState('');

	// Listas
	const [dataTipoTransacao, setDataTipoTransacao] = useState([]);
	const [dataCategoria, setDataCategoria] = useState([]);
	const [dataCartao, setDataCartao] = useState([]);


	//puxando os categorias e cartões
	useEffect(() => {
		// Listar cartões do usuários
		api.get(`/cards/${authData.token}`)
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

	//adicona pontuações na data de nascimento
    const adicionarPontuacaoDate = (text) => {
        const formattedDate = formatDate(text);
        setData(formattedDate);
    };

	async function cadastroTransacao()
	{
		if (titulo != "" && valor != "" && cartao != "" && data != "" && categoria != "")
		{
			const dataParaAPI = convertDateToAPIFormat(data);

			//realizando cadastro na API
			await api.post("/transacoes",
			{
				titulo: titulo,
				valor: valor,
				numeroCartao: cartao,
				dataTransacao: dataParaAPI,
				categoria: categoria,
				tipoTransacao: tipoTransacao
			}
		).then(function (response) {
			//Informa que o cadastro foi um sucesso e direciona para a pagina de login
			Alert.alert('Cadastro realizado com sucesso!', 'Sua Transação foi inserido com sucesso!');
			navigation.goBack();
		}).catch(function (error){
			//caso o banco retorne um erro, irá aparesentar a mensagem para ajustar no cadastro
			Alert.alert(
				'Cadastro não realizado!',
				error.response.headers.mensagem);
		});
		}
	}

  return(
			<View style={styles.container}>
				<Text style={styles.title}>NOVO REGISTRO</Text>

			<InputText
				onChangeText={setTitulo}
				value={titulo}
				placeholder="Titulo"
				maxLength={20}
				placeholderTextColor="#727272"
			/>

			<MaskedInput
				type={'money'}
				value={valor}
				onChangeText={text => setValor(text)}
				style={styles.input}
        keyboardType="numeric"
				placeholder="Digite o valor"
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
				value={cartao}
				data={dataCartao}
				placeholder="Cartão"
				placeholderTextColor="#727272"
				labelField="nome"
				valueField="numero"
				onChange={item => {
					setCartao(item.numero)
				}}
			/>	

			<InputText
				onChangeText={adicionarPontuacaoDate}
				value={data}
				placeholder="Data"
				keyboardType="numeric"
				placeholderTextColor="#727272"
			/>

			<InputSelect
				value={categoria}
				data={dataCategoria}
				placeholder="Categoria"
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