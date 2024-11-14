import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import CreditCard, { CARD_SIDE } from './CreditCard';
import { formatValue } from "../utils/formatValue";
import { useSharedValue } from "react-native-reanimated";

import { Feather, Entypo, Ionicons } from '@expo/vector-icons';

export default function CardRegistro({titulo, valor, categoria, data, tipoTransacao}){
    return(
        <View style={styles.cardRegistro}>
            <Text style={styles.itemRegistro}>{titulo}</Text>
            
            { tipoTransacao == 'DESPESA' ? 
              (<Text style={[styles.itemRegistro, {color:'red'}]}> {formatValue(valor)}</Text>) :
              (<Text style={[styles.itemRegistro, {color:'#00a8f3'}]}> {formatValue(valor)} </Text>)
            }
            <Text style={styles.itemRegistro}>{categoria}</Text>
            <Text style={styles.itemRegistro}>{data}</Text>
        </View>
    )
}

export function CardCartao({nomeCartao, numeroCartao, validade, cvv}){

  const cardSide = useSharedValue(CARD_SIDE.front) // Estado do Cartão entre Frente ou Verso

  function showFrontCard(){
    cardSide.value = CARD_SIDE.front
  }

  function showBackCard(){
    cardSide.value = CARD_SIDE.back
  }

  function handleFlipCard(){
    if(cardSide.value === CARD_SIDE.front){
      showBackCard()
    }else{
      showFrontCard()
    }
  }

  return(
    <View style={styles.cardWrapper}>
      <View>
        <CreditCard 
          cardSide={cardSide}
          nomeCartao={nomeCartao}
          numeroCartao={numeroCartao}
          validade={validade}
          />
        <TouchableOpacity style={styles.button} onPress={handleFlipCard}>
          <Ionicons name='refresh-outline' color={'#black'} size={26} /> 
        </TouchableOpacity>

      </View>
    </View>
  )
}


export function CardConta({saldo, banco, onpress}){

  return(
    <View style={styles.cardRegistro}>
      <Text style={styles.itemRegistro}>{banco}</Text>
      <Text style={styles.itemRegistro}>{formatValue(saldo)}</Text>
    </View>
  );

}

const styles = StyleSheet.create({
  cardRegistro:{
    width: '100%',
    minHeight: 85,
    height: 'auto',
    borderRadius: 10,
    marginTop: 10,
    alignItems:'center',
    justifyContent:'space-evenly',
    backgroundColor: '#474747',
    flexDirection: 'row',
  },
  itemRegistro:{
    maxWidth: 90,
    marginLeft: 5,
    height:'auto',
    fontSize: 14,
    fontWeight: 'bold',
    color:'#fff',
    textAlign:'center',
    flexWrap: 'wrap',
  },
  cardWrapper: {
    margin:'10',
    alignItems: 'center',
  },
  cardItem:{
    backgroundColor:'black',
    width:'195',
    marginLeft:'1',
    alignItems: 'center',
  },
  
  button: {
    width:'26',
    borderRadius:'50%',
    alignItems: "center",
    marginTop: -10,
    backgroundColor:'#02a128'
  },
  buttonView: {
    backgroundColor: "#727272",
    paddingHorizontal: 8, 
    borderRadius: 10, 
    fontSize: 16,
  }
});