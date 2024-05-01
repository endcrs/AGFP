import { StyleSheet, Text, View } from "react-native";
import { InputText } from "../components/InputText";
import { useState } from "react";
import Button from "../components/Button";
import { useNavigation } from "@react-navigation/native";


export default function CadastroResgistro() {

    const navigation = useNavigation();
    
    const [Titulo, setTitulo] = useState('');
    const [valor, setValor] = useState('');
    const [cartao, setCartao] = useState('');
    const [data, setData] = useState('');
    const [catergoria, setCategoria] = useState('');

    return(
        <View style={styles.container}>
          <Text style={styles.title}>NOVO REGISTRO</Text>

          <InputText
            onChangeText={setTitulo}
              value={Titulo}
            placeholder="Titulo"
            placeholderTextColor="#727272"
          />

          <InputText
            onChangeText={setValor}
              value={valor}
            placeholder="Valor"
            placeholderTextColor="#727272"
          />

          <InputText
            onChangeText={setCartao}
            value={cartao}
            placeholder="Cartão"
            placeholderTextColor="#727272"
          />
          <InputText
            onChangeText={setData}
            value={data}
            placeholder="Data"
            placeholderTextColor="#727272"
          />
          <InputText
            onChangeText={setCategoria}
            value={catergoria}
            placeholder="Categoria"
            placeholderTextColor="#727272"
          />
          <Button
            style={{marginTop:20, width:150}}
            title='Salvar'
            onPress={() => navigation.goBack()}
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