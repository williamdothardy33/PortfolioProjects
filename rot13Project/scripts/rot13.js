function rot13(str) {
    let letterRegex = /[A-Z]/g;
    let endPoint = "Z".charCodeAt(0);
    let beginningPoint = "A".charCodeAt(0) - 1;
    let myStr = str.replace(letterRegex, match => {
        let shiftedCode = match.charCodeAt(0) + 13;
        let shiftedLetter;
        if (shiftedCode > endPoint) {
            shiftedLetter = String.fromCharCode(beginningPoint + (shiftedCode - endPoint));
        }else {
            shiftedLetter = String.fromCharCode(shiftedCode); 
        }
        return shiftedLetter;
    });
    
    return myStr;
  }
  
  console.log(rot13("SERR PBQR PNZC"));