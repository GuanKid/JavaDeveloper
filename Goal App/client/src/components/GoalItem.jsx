import { useDispatch } from 'react-redux'
import { updateGoal, deleteGoal } from '../features/goals/goalSlice'
import { toast } from 'react-toastify'

function GoalItem({ goal, onGoalDeleted }) {
  const dispatch = useDispatch()

  // Handle delete action
  const handleDelete = async () => {
    const resultAction = await dispatch(deleteGoal(goal.id)) // Dispatch the delete action
    if (deleteGoal.fulfilled.match(resultAction)) {
      onGoalDeleted() // Notify the parent component of the deletion
      toast.success("Goal successfully deleted")
    }
  }

  // Handle update action
  const handleMarkAsDone = () => {
    const updatedGoalData = { ...goal, done: true }
    dispatch(updateGoal(goal.id, updatedGoalData))
  }

  return (
    <div className='goal'>
      <div>{new Date(goal.createdAt).toLocaleString()}</div>
      
      <h2>
      {goal.done === true ? <s>{goal.text}</s> : goal.text}
    </h2>

      <button onClick={handleDelete} className='close'>
        X
      </button>

    </div>
  )
}

export default GoalItem
