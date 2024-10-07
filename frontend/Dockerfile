FROM node:18-alpine

# Install pnpm globally
RUN npm install -g pnpm

# Set working directory
WORKDIR /app

# Copy package.json and pnpm-lock.yaml
COPY package.json pnpm-lock.yaml ./

# Install dependencies
RUN pnpm install

# Copy the rest of the files
COPY . .

# Build the app
RUN pnpm run build

# Expose the port
EXPOSE 3500

# Start the app
CMD ["pnpm", "run", "preview"]