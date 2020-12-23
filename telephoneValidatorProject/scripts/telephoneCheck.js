function telephoneCheck(str) {
    let telephoneRegex = /^1?[\s]{0,1}(\d\d\d|\(\d\d\d\))[\s-]{0,1}\d\d\d[\s-]?\d\d\d\d$/;
    return telephoneRegex.test(str);
  }
  
  console.log(telephoneCheck("555-555-5555"));