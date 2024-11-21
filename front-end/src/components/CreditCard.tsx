import { StyleSheet, Text, View } from "react-native";
import Animated, { useAnimatedStyle, SharedValue, interpolate, withTiming } from "react-native-reanimated";

// Tipagem de dado da função
type CreditCardProps = {
    cardSide: SharedValue<number>
    nomeCartao: string;
    nomeUsuario: string;
    numeroCartao: string;
    validade: string;
    cvv: string;
}

// Valores da frente e do verso do cartão
export enum CARD_SIDE {
    front = 0,
    back = 1,
}

export default function CreditCard({ cardSide, nomeCartao, numeroCartao, validade }: CreditCardProps){
    // Animações do giro do cartão
    const frontAnimatedStyle = useAnimatedStyle(() => {
        const rotateValue = interpolate(cardSide.value, [CARD_SIDE.front, CARD_SIDE.back], [0, 180])

        return {
            transform: [
                {rotateY: withTiming(`${rotateValue}deg`, {
                    duration: 1000
                })}
            ]
        }
    })

    const backAnimatedStyle = useAnimatedStyle(() => {
        const rotateValue = interpolate(cardSide.value, [CARD_SIDE.front, CARD_SIDE.back], [180, 360])

        return {
            transform: [
                {rotateY: withTiming(`${rotateValue}deg`, {
                    duration: 1000
                })}
            ]
        }
    })

    return(
        <View>
            <Animated.View style={[styles.card, styles.front, frontAnimatedStyle]}>
                <View style={styles.header}>
                    <View style={[styles.circle, styles.logo]}/>
                    <Text>{nomeCartao}</Text>
                </View>

                <View style={styles.footer}>
                    <Text></Text>

                    <View style={styles.flag}>
                        <View style={[styles.circle, styles.red]}/>
                        <View style={[styles.circle, styles.orange]}/>
                    </View>
                </View>
            </Animated.View>

            <Animated.View style={[styles.card, styles.back, backAnimatedStyle]}>
                <View>
                    <Text style={styles.label}>Número do Cartão</Text>
                    <Text style={styles.value}>{numeroCartao}</Text>
                </View>

                <View style={styles.footer}>
                    <View>
                        <Text style={styles.label}>Validade</Text>
                        <Text style={styles.value}>{validade}</Text>
                    </View>
                </View>
            </Animated.View>
        </View>
    );
}

const styles = StyleSheet.create({
    card: {
        width: 370,
        height: 200,
        borderRadius: 12,
        padding: 24,
        marginTop: 12,
        justifyContent: "space-between"
    },
    front: {
        backgroundColor: "#DAE1E7",
        backfaceVisibility: "hidden",
        position: "absolute"
    },
    back: {
        backgroundColor: "#BAC1C7",
        backfaceVisibility: "hidden"
    },
    circle: {
        width: 24,
        height: 24,
        borderRadius: 12,
    },
    logo: {
        backgroundColor: "#8795A0",
    },
    header: {
        flexDirection: "row",
        alignItems: "center",
        gap: 7,
    },
    name: {
        fontSize: 16,
        fontWeight: "bold",
    },
    footer: {
        flexDirection: "row",
        justifyContent: 'space-between'
    },
    flag: {
        flexDirection: "row",
        gap: -12,
    },
    red: {
        backgroundColor: "red",
    },
    orange: {
        backgroundColor: "orange",
    },
    label: {
        fontSize: 18,
    },
    value: {
        fontSize: 16,
    }
  });