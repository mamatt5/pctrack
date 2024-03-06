export const generatePassword = (length) => {
    const charset = "!@#$%^abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    let password = "";
    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * charset.length);
      password += charset[randomIndex];
    }
    return password;
  };


export function subtractArrays(arr1, arr2) {
    const map = new Map();

    // Create a map of unique roomIds from arr2
    for (const obj of arr2) {
      map.set(obj.roomId, true);
    }

    // Filter out elements from arr1 that are also in arr2
    const result = arr1.filter(obj => !map.has(obj.roomId));
    return result;
  }