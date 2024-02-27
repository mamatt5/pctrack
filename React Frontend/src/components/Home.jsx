import { Link } from 'react-router-dom'

const Home = () => {

    return (
        <>
            <h5> basic footer </h5>
            <Link to="/login" > <div> login </div> </Link>
            <Link to="/admin" > admin</Link>
        </>
    )
}

export default Home