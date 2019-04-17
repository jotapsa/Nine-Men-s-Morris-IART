//Just run this as node trying.js
//It's just to test random code.

const readline = require("readline");

readline.emitKeypressEvents(process.stdin);
if (process.stdin.isTTY) {
  process.stdin.setRawMode(true);
}

process.stdin.addListener("keypress", (str, key) => {
  console.log("str - ", str);
  console.log("key - ", key);

  if (key && key.ctrl && key.name === 'c') {
    process.exit();
  }

});
