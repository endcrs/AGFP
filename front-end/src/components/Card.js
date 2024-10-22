import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import CreditCard, { CARD_SIDE } from './CreditCard';
import { formatValue } from "../utils/formatValue";
import { useSharedValue } from "react-native-reanimated";

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
    console.log(cardSide.value)
    if(cardSide.value === CARD_SIDE.front){
      showBackCard()
    }else{
      showFrontCard()
    }
  }

  return(
    <View>
      <CreditCard 
        cardSide={cardSide}
        nomeCartao={nomeCartao}
        numeroCartao={numeroCartao}
        validade={validade}
        cvv={cvv}
      />
      <TouchableOpacity style={styles.button} onPress={handleFlipCard}>
        <View style={styles.buttonView}>
          <Text style={{color: "#FFF", fontSize: 16,}}>Inverter</Text>
        </View>
      </TouchableOpacity>
    </View>
  )
}


const styles = StyleSheet.create({
  cardRegistro:{
    width: '100%',
    minHeight: 85,
    height: 'auto',
    borderRadius: 10,
    marginTop: 10,
    backgroundColor: '#474747',
    justifyContent: 'space-between',
    alignItems: 'center',
    flexDirection: 'row',
  },
  itemRegistro:{
    width: 90,
    height:'auto',
    fontSize: 14,
    fontWeight: 'bold',
    color:'#fff',
    textAlign:'center',
    flexWrap: 'wrap',
  },
  button: {
    alignItems: "center",
    marginBottom: 24,
    marginTop: -10
  },
  buttonView: {
    backgroundColor: "#727272",
    paddingHorizontal: 8, 
    borderRadius: 10, 
    fontSize: 16,
  }
});