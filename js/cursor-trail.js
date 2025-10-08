// Canvas setup
const canvas = document.createElement("canvas");
canvas.id = "cursor-trail";
document.body.appendChild(canvas);

const ctx = canvas.getContext("2d");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

window.addEventListener("resize", () => {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
});

// Particle array
let particles = [];
const colors = ["#66fcf1", "#45a29e", "#ffffff"];

document.addEventListener("mousemove", (e) => {
  for (let i = 0; i < 4; i++) {
    particles.push(new Particle(e.x, e.y));
  }
});

class Particle {
  constructor(x, y) {
    this.x = x;
    this.y = y;
    this.size = Math.random() * 4 + 1;
    this.color = colors[Math.floor(Math.random() * colors.length)];
    this.speedX = Math.random() * 3 - 1.5;
    this.speedY = Math.random() * 3 - 1.5;
    this.alpha = 1;
  }

  update() {
    this.x += this.speedX;
    this.y += this.speedY;
    this.alpha -= 0.02;
  }

  draw() {
    ctx.globalAlpha = this.alpha;
    ctx.fillStyle = this.color;
    ctx.beginPath();
    ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
    ctx.fill();
    ctx.globalAlpha = 1;
  }
}

function animate() {
  ctx.fillStyle = "rgba(11, 12, 16, 0.1)";
  ctx.fillRect(0, 0, canvas.width, canvas.height);

  particles.forEach((p, i) => {
    p.update();
    p.draw();

    // Remove faded particles
    if (p.alpha <= 0) {
      particles.splice(i, 1);
    }
  });

  requestAnimationFrame(animate);
}

animate();
