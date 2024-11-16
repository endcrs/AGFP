import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import CreditCard, { CARD_SIDE } from './CreditCard';
import { formatValue } from "../utils/formatValue";
import { useSharedValue } from "react-native-reanimated";

import { Feather, Entypo, Ionicons } from '@expo/vector-icons';
import { convertDateToFormFormat } from "../utils/formatData";

export default function CardRegistro({titulo, valor, categoria, data, tipoTransacao, onPress}){
    return(
        <View style={styles.cardRegistro}>
            <Text style={styles.itemRegistro}>{titulo}</Text>
            
            { tipoTransacao == 'DESPESA' ? 
              (<Text style={[styles.itemRegistro, {color:'red'}]}> {formatValue(valor)}</Text>) :
              (<Text style={[styles.itemRegistro, {color:'#00a8f3'}]}> {formatValue(valor)} </Text>)
            }

            { categoria == 'Nula' ?
              (<Text style={styles.itemRegistro}>Receita</Text>):
              (<Text style={styles.itemRegistro}>{categoria}</Text>)
            }
            
            <Text style={styles.itemRegistro}>{convertDateToFormFormat(data)}</Text>
            
            <View style={styles.itemPlaceholder}></View>

            <TouchableOpacity style={[ styles.deleteTransatiction]} onPress={onPress}>
              <Feather name='delete' color={'red'} size={24} />
            </TouchableOpacity>

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


export function CardConta({saldo, banco, data}){

  return(
    <View style={styles.cardRegistro}>
      <Text style={styles.itemRegistro}>{banco}</Text>
      <Text style={styles.itemRegistro}>{formatValue(saldo)}</Text>
      <Text style={styles.itemRegistro}>{convertDateToFormFormat(data)}</Text>
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
    justifyContent:'space-around',
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
  itemPlaceholder:{
    width: 32,
    height: 10,
  },  
  deleteTransatiction:{
    position: 'absolute',
    right: 5,
    top:'50%',
    transform: [{ translateY: -10 }]
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