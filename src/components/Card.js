import { StyleSheet, Text, View } from "react-native";

export default function CardRegistro({titulo, valor, categoria, data}){
    return(
        <View style={styles.cardRegistro}>
            <Text style={styles.itemRegistro}>{titulo}</Text>
            <Text style={styles.itemRegistro}>R$ {valor}</Text>
            <Text style={styles.itemRegistro}>{categoria}</Text>
            <Text style={styles.itemRegistro}>{data}</Text>
        </View>
    )
}


const styles = StyleSheet.create({
  cardRegistro:{
    width: '100%',
    minHeight: 85,
    height: 'auto',
    borderRadius: 10,
    marginTop: 15,
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
  }
});