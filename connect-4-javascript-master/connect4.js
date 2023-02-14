const 
    COLUMNS = 7,
    ROWS = 6,
    EMPTY_SPACE = " ",
    PLAYER_1 = "o",
    PLAYER_2 = "x",
    PLAYER_CPU = PLAYER_2,
    CONNECT = 4; // <-- Change this and you can play connect 5, connect 3, connect 100 and so on!
    
new Vue({
    el: "#app",
    data: () => ({
        board: [],
        COLUMNS,
        ROWS,
        PLAYER_1,
        PLAYER_2,
        EMPTY_SPACE,
        currentPlayer: null,
        canPlay: false,
        ContadorP1:0,
        ContadorP2:0,
    }),
    async mounted() {
        await Swal.fire(
            '4 EN RAYA',
            'INTRODUCE TU FICHA',
        );
        this.resetGame();
    },
    methods: {
            async resetGame() {
            this.fillBoard();
            this.selectPlayer();
            this.canPlay=true;
        },

        async play(columnIndex){
            if (this.isFinJuego(this.board)) {
                await Swal.fire({
                  title: "Fin del juego",
                  text: "El tablero está lleno, juego terminado."
                });
                this.resetGame();
                return;
              } 
        },

    
        countUp(x, y, player, board) {
            let startY = (y - CONNECT >= 0) ? y - CONNECT + 1 : 0;
            let counter = 0;
            for (; startY <= y; startY++) {
                if (board[startY][x] === player) {
                    counter++;
                } else {
                    counter = 0;
                }
            }
            return counter;
        },
        countRight(x, y, player, board) {
            let endX = (x + CONNECT < COLUMNS) ? x + CONNECT - 1 : COLUMNS - 1;
            let counter = 0;
            for (; x <= endX; x++) {
                if (board[y][x] === player) {
                    counter++;
                } else {
                    counter = 0;
                }
            }
            return counter;        },
        countUpRight(x, y, player, board) {
            let endX = (x + CONNECT < COLUMNS) ? x + CONNECT - 1 : COLUMNS - 1;
            let startY = (y - CONNECT >= 0) ? y - CONNECT + 1 : 0;
            let counter = 0;
            while (x <= endX && startY <= y) {
                if (board[y][x] === player) {
                    counter++;
                } else {
                    counter = 0;
                }
                x++;
                y--;
            }
            return counter;
        },
        countDownRight(x, y, player, board) {
            let endX = (x + CONNECT < COLUMNS) ? x + CONNECT - 1 : COLUMNS - 1;
            let endY = (y + CONNECT < ROWS) ? y + CONNECT - 1 : ROWS - 1;
            let counter = 0;
            while (x <= endX && y <= endY) {
                if (board[y][x] === player) {
                    counter++;
                } else {
                    counter = 0;
                }
                x++;
                y++;
            }
            return counter;
        },
        isWinner(player, board) {
            let Contador= null;
            for (let y = 0; y < ROWS; y++) {
                for (let x = 0; x < COLUMNS; x++) {
                 //  for(CONNECT; 6 ; CONNECT++)
                    let count = 0;
                    count = this.countUp(x, y, player, board);
                    if (count >= CONNECT){
                        Contador++;
                        if(count >=6){
                        Contador--; 
                        }
                      };

                    count = this.countRight(x, y, player, board);
                    if (count >= CONNECT){
                        Contador++;
                        if(count >=6){
                            Contador--; 
                            }
                      };

                    count = this.countUpRight(x, y, player, board);
                    if (count >= CONNECT){
                    Contador++;
                    if(count >=6){
                        Contador--; 
                        }
                      };

                    count = this.countDownRight(x, y, player, board);
                    if (count >= CONNECT){
                     Contador++;
                     if(count >=6){
                        Contador--; 
                        }

                      };
                    
                }
                
            }
            return Contador;
 
       },
        isFinJuego(board) {
            for (let y = 0; y < ROWS; y++) {
                for (let x = 0; x < COLUMNS; x++) {
                    const currentCell = board[y][x];
                    if (currentCell === EMPTY_SPACE) {
                        return false;
                    }
                }
            }
            return true;
        },
        getRandomNumberBetween(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
        selectPlayer() {
            if (this.getRandomNumberBetween(0, 1) === 0) {
                this.currentPlayer = PLAYER_1;
            } else {
                this.currentPlayer = PLAYER_2;
            }
        },
        togglePlayer() {
            this.currentPlayer = this.getAdversary(this.currentPlayer);
        },
        getAdversary(player) {
            if (player === PLAYER_1) {
                return PLAYER_2;
            } else {
                return PLAYER_1;
            }
        },
        fillBoard() {
            this.board = [];
            for (let i = 0; i < ROWS; i++) {
                this.board.push([]);
                for (let j = 0; j < COLUMNS; j++) {
                    this.board[i].push(EMPTY_SPACE);
                }
            }
        },
        cellImage(cell) {
            if (cell === this.PLAYER_1) {
                return "img/player1.png";
            } else if (cell === this.PLAYER_2) {
                return "img/player2.png";
            } else {
                return "img/empty.png"
            }
        },
        async makeMove(columnNumber) {
            const columnIndex = columnNumber - 1;
            const firstEmptyRow = this.getFirstEmptyRow(columnIndex, this.board);
            if (firstEmptyRow === -1) {
                Swal.fire('Columna llena, inserte la ficha en una columna con espacios libres');
                return;
            }
            Vue.set(this.board[firstEmptyRow], columnIndex, this.currentPlayer);
            const status = await this.checkGameStatus();
            if (!status) {
                this.togglePlayer();
            } else {
                this.askUserForAnotherMatch();
            }
        },
        // Returns true if there's a winner or a tie. False otherwise
        async checkGameStatus() {
        
            if (this.isFinJuego(this.board)) {

            ContadorP1=this.isWinner(PLAYER_1, this.board);
            ContadorP2=this.isWinner(PLAYER_2, this.board);

                await this.showFinJuego();

                return true;
            }
            return false;
        },
        async askUserForAnotherMatch() {
            this.canPlay = false;
            const result = await Swal.fire({
                title: 'Play again?',
                text: "¿Quieres jugar otra partida?",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#fdbf9c',
                cancelButtonColor: '#4A42F3',
                cancelButtonText: 'No',
                confirmButtonText: 'Yes'
            });
            if (result.value) {
                this.resetGame();
            }
        },

        async showFinJuego() {
            if (ContadorP1 > ContadorP2) {
                await Swal.fire({
                  title: "Fin del juego",
                  text: "El ganador es el jugador 1 con " + ContadorP1 + " puntos "
                });
              } else if (ContadorP2 > ContadorP1) {
                await Swal.fire({
                  title: "Fin del juego",
                  text: "El ganador es el jugador 2 con " + ContadorP2 + " puntos "
                });
              } else {
                await Swal.fire({
                  title: "Fin del juego",
                  text: "Empate con " + ContadorP1 + " puntos cada jugador."
                });
              }
        },

        getFirstFilledRow(columnIndex, board) {
            for (let i = ROWS - 1; i >= 0; i--) {
                if (board[i][columnIndex] !== EMPTY_SPACE) {
                    return i;
                }
            }
            return -1;
        },
        getFirstEmptyRow(columnIndex, board) {
            for (let i = ROWS - 1; i >= 0; i--) {
                if (board[i][columnIndex] === EMPTY_SPACE) {
                    return i;
                }
            }
            return -1;
        }
    }
});