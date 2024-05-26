const formatDate = (text) => {
    // Remove tudo que não for dígito
    let cleaned = ('' + text).replace(/\D/g, '');

    // Limita o tamanho da entrada a 8 dígitos (ddmmyyyy)
    if (cleaned.length > 8) {
        cleaned = cleaned.slice(0, 8);
    }

    // Divide a string em partes conforme a necessidade
    let formatted = cleaned;
    if (cleaned.length >= 5) {
        formatted = cleaned.slice(0, 2) + '/' + cleaned.slice(2, 4) + '/' + cleaned.slice(4, 8);
    } else if (cleaned.length >= 3) {
        formatted = cleaned.slice(0, 2) + '/' + cleaned.slice(2, 4);
    } else if (cleaned.length >= 1) {
        formatted = cleaned.slice(0, 2);
    }

    return formatted;
};

const convertDateToAPIFormat = (date) => {

    //separando dia mes e ano para enviar a API
    const [day, month, year] = date.split('/');
    //confirmando se exite o mes dia e ano
    if (day && month && year) {
        return `${year}-${month}-${day}`;
    }
    return null;
};

const convertDateToFormFormat = (date) => {

    //separando dia mes e ano para enviar a API
    const [year, month, day] = date.split('-');
    //confirmando se exite o mes dia e ano
    if (year && month && day) {
        return `${day}/${month}/${year}`;
    }
    return null;
};

export {formatDate, 
        convertDateToAPIFormat,
        convertDateToFormFormat}
