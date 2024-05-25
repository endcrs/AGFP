const formatCardNumber = (text) => {
    // Remover todos os caracteres que não são dígitos
    const digitsOnly = text.replace(/\D/g, '');

    // Definir o padrão de formatação: agrupar os números em blocos de 4 dígitos
    const formatted = digitsOnly.replace(/(\d{4})/g, '$1 ').trim();
    
    return formatted;
};

const formatValidate = (text) => {
    // Verifica se a data de validade tem pelo menos 4 caracteres
    if (text.length < 4) {
        return text; // Retorna a data de validade sem formatação se for inválida
    }

    // Extrai o mês e o ano da data de validade
    const month = text.substring(0, 2);
    const year = text.substring(2, 4);

    // Retorna a data de validade formatada
    return `${month}/${year}`;
};

export {formatCardNumber, formatValidate}