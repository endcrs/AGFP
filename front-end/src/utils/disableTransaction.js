import { Alert } from "react-native";
import api from "../services/api"


const disableTransactionDialog = (idT) => {

  Alert.alert(
    "Deseja eliminar está transanção?", // Título da caixa de diálogo
    "Ao eliminar, não será possivel vê-la novamente!", // Mensagem
    [
      {
        text: "Não", // Botão "Não"
        style: "cancel", // Define estilo do botão como "cancel"
      },
      {
        text: "Sim", // Botão "Sim"
        onPress: () => disableTransaction(idT),
      },
    ],
    { cancelable: false } // Impede que o usuário feche tocando fora da caixa
  );
  
    
}

async function disableTransaction(idT){
  await api.put(`/transactions/disable-transaction/${idT}`)
    .then(function (response) {
      Alert.alert('Sucesso!', 'A transação foi elimidade com sucesso!');
    })
    .catch(function (error) {
      Alert.alert('Exclusão não realizada!', 'A transação foi elimidade com sucesso!');

      console.log(error.response.headers);
      
    });
}

export {disableTransactionDialog};