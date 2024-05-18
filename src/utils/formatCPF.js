const formatCPF = (text) => {
    const cleanedText = text.replace(/\D/g, '');

    // CPF sem pontuações
    let formattedCPF = cleanedText;

    //colocando um ponto depois dos 3 digitos do cpf
    if (cleanedText.length > 3) {
        formattedCPF = `${cleanedText.slice(0, 3)}.${cleanedText.slice(3)}`;
    }
    //colocando um ponto depois dos 7 digitos do cpf
    if (cleanedText.length > 6) {
        formattedCPF = `${formattedCPF.slice(0, 7)}.${formattedCPF.slice(7)}`;
    }
    //colocando um traço depois de 11 digitos do cpf
    if (cleanedText.length > 9) {
        formattedCPF = `${formattedCPF.slice(0, 11)}-${formattedCPF.slice(11)}`;
    }

    return formattedCPF;
}

export { formatCPF };