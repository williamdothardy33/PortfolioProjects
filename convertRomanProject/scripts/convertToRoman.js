function convertToRoman(number) {
   let romanObj = {
       1: 'I',
       2: 'II',
       3: 'III',
       4: 'IV',
       5: 'V',
       6: 'VI',
       7: 'VII',
       8: 'VIII',
       9: 'IX',
       10: 'X',
       20: 'XX',
       30: 'XXX',
       40: 'XL',
       50: 'L',
       60: 'LX',
       70: 'LXX',
       80: 'LXXX',
       90: 'XC',
       100: 'C', 
       200: 'CC',
       300: 'CCC',
       400: 'CD',
       500: 'D',
       600: 'DC',
       700: 'DCC',
       800: 'DCCC',
       900: 'CM',
       1000: 'M'
   };
   let romanNumeral = "";
   let sortedRomanValuesArray = Object.keys(romanObj).map(key => parseInt(key)).sort((a, b) => b - a).map(intKey => intKey.toString());
   let remainder = number;
   let index = 0;
   let lastIndex = sortedRomanValuesArray.length - 1

   while(remainder > 0 && index <= lastIndex) {
       let romanKey = sortedRomanValuesArray[index];
       let quotient = Math.floor(remainder / parseInt(romanKey)); 
       if (1 <= quotient && quotient <= 3) {
        romanNumeral += romanObj[romanKey].repeat(quotient);
        remainder %= romanKey;
       }
       index++;
   }

   return romanNumeral;
}


 //should return "II".
console.log(convertToRoman(2));

convertToRoman(3); //should return "III".

convertToRoman(4); //should return "IV".

 //should return "V".
console.log(convertToRoman(5));

convertToRoman(9); //should return "IX".

convertToRoman(12); //should return "XII".

 //should return "XVI".
console.log(convertToRoman(16));

convertToRoman(29); //should return "XXIX".

convertToRoman(44); //should return "XLIV".

 //should return "XLV"
console.log(convertToRoman(45));

convertToRoman(68); //should return "LXVIII"

convertToRoman(83); //should return "LXXXIII"

 //should return "XCVII"
console.log(convertToRoman(97));

convertToRoman(99); //should return "XCIX"

convertToRoman(400); //should return "CD"

 //should return "D"
console.log(convertToRoman(500));

convertToRoman(501); //should return "DI"

convertToRoman(649); //should return "DCXLIX"

 //should return "DCCXCVIII"
console.log(convertToRoman(798));

convertToRoman(891); //should return "DCCCXCI"

convertToRoman(1000); //should return "M"

 //should return "MIV"
console.log(convertToRoman(1004));

convertToRoman(1006); //should return "MVI"

convertToRoman(1023); //should return "MXXIII"

 //should return "MMXIV"
console.log(convertToRoman(2014));

 //should return "MMMCMXCIX"
console.log(convertToRoman(3999));


