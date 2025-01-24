import axios from 'axios'

const API_URL = 'goal/'

// Create new goal
const createGoal = async (goalData, token) => {
  const config = {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  }

  const response = await axios.post(API_URL + 'create', goalData, config)

  return response.data
}

// Get user goals
const getGoals = async (token) => {
  const config = {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  }

  const response = await axios.get(API_URL + 'all', config)

  return response.data
}

// Update a goal
const updateGoal = async (goalId, goalData, token) => {
  const config = {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  }
  const response = await axios.put(API_URL + goalId, goalData, config)
  return response.data
}

// Delete user goal
const deleteGoal = async (goalId, token) => {
  const config = {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  }

  const response = await axios.delete(API_URL + goalId, config)

  return response.data
}

const goalService = {
  createGoal,
  getGoals,
  deleteGoal,
  updateGoal, // Add the new service
}

export default goalService
