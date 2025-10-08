// Create canvas
const canvas = document.createElement("canvas");
canvas.id = "cursor-trail";
document.body.appendChild(canvas);

const ctx = canvas.getContext("2d");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

// Resize with window
window.addEventListener("resize", () => {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
});

let mouse = { x: null, y: null };
document.addEventListener("mousemove", (e) => {
  mouse.x = e.x;
  mouse.y = e.y;
});

class Particle {
  constructor() {
    this.x = Math.random() * canvas.width;
    this.y = Math.random() * canvas.height;
    this.size = Math.random() * 4 + 1;
    this.color = "rgba(102, 252, 241, 0.15)";
    this.speed = Math.random() * 1.5 + 0.5;
  }

  update() {
    if (mouse.x && mouse.y) {
      // Move smoothly toward cursor
      this.x += (mouse.x - this.x) * 0.02;
      this.y += (mouse.y - this.y) * 0.02;
    }

    // Slight drifting for mist effect
    this.x += Math.sin(Date.now() * 0.001 + this.y) * 0.1;
    this.y += Math.cos(Date.now() * 0.001 + this.x) * 0.1;
  }

  draw() {
    ctx.beginPath();
    ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
    ctx.fillStyle = this.color;
    ctx.fill();
  }
}

let particles = [];
for (let i = 0; i < 100; i++) {
  particles.push(new Particle());
}

function animate() {
  ctx.fillStyle = "rgba(11, 12, 16, 0.07)";
  ctx.fillRect(0, 0, canvas.width, canvas.height);

  particles.forEach((p) => {
    p.update();
    p.draw();
  });

  requestAnimationFrame(animate);
}

animate();
