let board = Array(9).fill('');
let currentPlayer = 'X';
let gameActive = true;

const boardDiv = document.getElementById('board');
const statusDiv = document.getElementById('status');

function renderBoard() {
  boardDiv.innerHTML = '';
  board.forEach((cell, idx) => {
    const cellDiv = document.createElement('div');
    cellDiv.className = 'cell';
    cellDiv.textContent = cell;
    cellDiv.onclick = () => handleMove(idx);
    boardDiv.appendChild(cellDiv);
  });
}

function handleMove(idx) {
  if (!gameActive || board[idx]) return;
  board[idx] = currentPlayer;
  if (checkWinner()) {
    statusDiv.textContent = `Player ${currentPlayer} wins!`;
    gameActive = false;
  } else if (board.every(cell => cell)) {
    statusDiv.textContent = "It's a draw!";
    gameActive = false;
  } else {
    currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
    statusDiv.textContent = `Player ${currentPlayer}'s turn`;
  }
  renderBoard();
}

function checkWinner() {
  const winPatterns = [
    [0,1,2],[3,4,5],[6,7,8], // rows
    [0,3,6],[1,4,7],[2,5,8], // cols
    [0,4,8],[2,4,6]          // diags
  ];
  return winPatterns.some(pattern =>
    pattern.every(idx => board[idx] === currentPlayer)
  );
}

function resetGame() {
  board = Array(9).fill('');
  currentPlayer = 'X';
  gameActive = true;
  statusDiv.textContent = `Player ${currentPlayer}'s turn`;
  renderBoard();
}

resetGame();
