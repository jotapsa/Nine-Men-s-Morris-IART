const readline = require('readline');

const GameState = require('./logic/gameState');
const GameController = require('./controller/game-controller');
const GameView = require('./view/cli').cliView;
const Player = require('./logic/player');
const Computer = require('./logic/computer');

const player0 = new Player(0);
const player1 = new Player(1);
const state = new GameState(player0, player1);
const controller = new GameController(state);

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  prompt: '> ',
});

GameView.render(state);
console.log("What's your move (0-23) ? ");
rl.prompt();

rl.on('line', (input) => {
  controller.input(input);
  GameView.render(state);
  console.log("What's your move (0-23) ? ");
  rl.prompt();
}).on('close', () => {
  console.log('Come again!');
  process.exit(0);
});
